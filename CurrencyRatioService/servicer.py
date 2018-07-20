from ratio_service_pb2 import Ratio, CurrenciesRatio
import ratio_service_pb2_grpc
from file_observer import FileChangedHandler
from queue import Queue, Empty
from typing import List, Tuple
from reader import read


class CurrencyInfo(ratio_service_pb2_grpc.CurrencyInfoServicer):
    def __init__(self, event_handler: FileChangedHandler, filename: str):
        super().__init__()
        self.event_handler = event_handler
        self.filename = filename

    def GetRatio(self, request, context):
        print("send to client current status for:", request.baseCurCode)
        filter_fun = filter_factory(request.baseCurCode, request.wantedCurCodes)
        raw_ratio = filter(filter_fun, read(self.filename))
        ratio = map(map_raw_ratio, raw_ratio)
        return CurrenciesRatio(ratio=list(ratio))

    def UpdateRatio(self, request, context):
        filter_fun = filter_factory(request.baseCurCode, request.wantedCurCodes)
        my_queue = Queue()
        print("client ", my_queue, " want updates for ", request.baseCurCode)
        context.add_callback(lambda: self.event_handler.unsubscribe(my_queue))
        self.event_handler.subscribe(my_queue)

        while context.is_active():
            try:
                # can block until new changes available or 10 s
                for single_currency in filter(filter_fun, my_queue.get(timeout=10)):
                    print("send updated info to client ", my_queue, *single_currency)
                    yield map_raw_ratio(single_currency)
            except Empty:
                pass
        else:
            print("client ", my_queue, " disconnected")


def filter_factory(base: str, wanted: List[str]):
    """check if client is interested in this currency"""

    def inner(to_filter: Tuple[str, str, float]):
        return to_filter[0] == base and to_filter[1] in wanted

    return inner


def map_raw_ratio(x):
    return Ratio(baseCur=x[0], otherCur=x[1], value=x[2])
