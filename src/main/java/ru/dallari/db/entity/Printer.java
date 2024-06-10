package ru.dallari.db.entity;

import ch.qos.logback.classic.Logger;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String IP, DNSname;
    private Integer scanCount, printCount;
    @ManyToOne
    private Location location;
    @ManyToOne
    private PrinterModel printerModel;
    @ManyToMany(mappedBy = "printers")
    private List<User> userList;
    @NonNull
    transient private boolean isOnline;

    public Printer() {
    }
    public Printer(String DNSname, String IP, Integer printCount, Integer scanCount, PrinterModel printerModel,Location location){
        this.DNSname = DNSname;
        this.IP = IP;
        this.printCount = printCount;
        this.scanCount = scanCount;
        this.printerModel = printerModel;
        this.location = location;
    }

    public String getPrinterSNMP(String OIDname) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("C:\\tools\\web\\oid.properties"));
        String response = "";
        OID oid = new OID(properties.getProperty(OIDname));

        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        transport.listen();

        CommunityTarget<Address> communityTarget = new CommunityTarget<>();
        communityTarget.setCommunity(new OctetString("public"));
        communityTarget.setVersion(SnmpConstants.version1);
        communityTarget.setAddress(new UdpAddress(this.IP + "/161"));
        communityTarget.setRetries(2);
        communityTarget.setTimeout(1000);

        PDU pdu = new PDU();
        pdu.add(new VariableBinding(oid));

        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));

        Snmp snmp = new Snmp(transport);
        ResponseEvent<Address> responseEvent = snmp.get(pdu, communityTarget);

        if (responseEvent != null)
        {
            PDU responsePDU = responseEvent.getResponse();

            if (responsePDU != null)
            {
                if (responsePDU.getErrorStatus() == PDU.noError)
                {
                    response = responsePDU.getVariableBindings().getFirst().toString();
                    if (response.contains("=")) {
                        response = response.split("= ")[1];
                    }
                    if (response.contains("ECOSYS")) {
                        response = response.split(" ")[1];
                    }

                }
                else
                {
                    response = null;
                }
            }
            else
            {
                response = null;

            }
        }
        else
        {
            response = null;
        }

        snmp.close();
        return response;
    }

    public boolean isOnline() {
        try {
            InetAddress inet = InetAddress.getByName(this.IP);
            isOnline = inet.isReachable(5000);
        } catch (UnknownHostException e) {
            System.out.println("Host is unknown");
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isOnline;
    }
}
