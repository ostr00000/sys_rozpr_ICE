import ratio_service_pb2_grpc
import grpc
import time
from concurrent.futures import ThreadPoolExecutor
from watchdog.observers import Observer
from file_observer import FileChangedHandler
from servicer import CurrencyInfo

max_clients = 10
_ONE_DAY_IN_SECONDS = 60 * 60 * 24
currency_path = 'currencies/currency.txt'
host = 'localhost'
port = 32323


def main():
    # watchdog
    event_handler = FileChangedHandler(currency_path)
    observer = Observer()
    observer.schedule(event_handler, path=currency_path.split("/")[0])
    observer.start()

    # currency info
    server = grpc.server(ThreadPoolExecutor(max_clients))
    info = CurrencyInfo(event_handler, currency_path)
    ratio_service_pb2_grpc.add_CurrencyInfoServicer_to_server(info, server)
    server.add_insecure_port(host + ':' + str(port))
    server.start()

    try:
        print("Currency server started")
        while True:
            time.sleep(_ONE_DAY_IN_SECONDS)
    except KeyboardInterrupt:
        server.stop(0)
        observer.stop()
    observer.join()


if __name__ == "__main__":
    main()
