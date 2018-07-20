from queue import Queue
from typing import Set, List, Tuple
from watchdog.events import FileSystemEventHandler
from reader import read


class FileChangedHandler(FileSystemEventHandler):
    def __init__(self, filename):
        super().__init__()
        self.filename = filename
        self.saved_data = []
        self.saved_data = self.get_changes()
        self.subscribers: Set[Queue] = set()
        print("start status:", self.saved_data)

    def on_modified(self, event):
        changes = self.get_changes()
        if changes:
            modified = [x[:2] for x in changes]
            self.saved_data = [x for x in self.saved_data
                               if x[:2] not in modified]
            self.saved_data.extend(changes)
            print("file modified - current status:", self.saved_data)
            for subscriber in self.subscribers:
                subscriber.put(changes)

    def subscribe(self, queue: Queue):
        self.subscribers.add(queue)

    def unsubscribe(self, queue: Queue):
        try:
            self.subscribers.remove(queue)
            print("account unsubscribe", queue)
        except KeyError:
            print("account has not subscribed")

    def get_changes(self) -> List[Tuple[str, str, float]]:
        return [line for line in read(self.filename)
                if line not in self.saved_data]
