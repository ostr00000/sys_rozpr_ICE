package bankModule;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import gBank.BankPrx;

import java.io.Closeable;


public class ClientConnection implements Closeable {
    private BankPrx bankPrx;
    private Communicator communicator;

    public ClientConnection(){
        this("tcp -h localhost -p 10000");
    }

    public ClientConnection(String proxyIpPort) {
        this(proxyIpPort, "base", "main");
    }

    private ClientConnection(String proxyIpPort, String name, String category) {
        this.communicator = Util.initialize();

        ObjectPrx objectPrx = this.communicator.stringToProxy(category + "/" + name + ":" + proxyIpPort);
        this.bankPrx = BankPrx.checkedCast(objectPrx);
        if (bankPrx == null) {
            throw new Error("Invalid proxy");
        }
    }

    public BankPrx getBank() {
        return bankPrx;
    }

    @Override
    public void close() {
        if (this.communicator != null) {
            this.communicator.close();
        }
    }
}
