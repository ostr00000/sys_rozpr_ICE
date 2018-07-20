package bankModule;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

import java.io.Closeable;

public class ServerConnection implements Closeable {
    private Communicator communicator;

    public static void main(String[] args) {
        // initialize BankData
        System.out.println("Base currency:" + BankData.baseCurrency);

        new ServerConnection();
    }

    private ServerConnection() {
        this(new Identity("base", "main"));
    }

    private ServerConnection(Identity baseIdentity) {
        this.communicator = Util.initialize();

        String address = "tcp -h localhost -p 10000";
        ObjectAdapter adapter = this.communicator.createObjectAdapterWithEndpoints("adapter", address);
        BankI bankServant = new BankI(adapter);
        adapter.add(bankServant, baseIdentity);
        adapter.activate();
    }

    @Override
    public void close() {
        if (this.communicator != null) {
            this.communicator.close();
        }
    }

}
