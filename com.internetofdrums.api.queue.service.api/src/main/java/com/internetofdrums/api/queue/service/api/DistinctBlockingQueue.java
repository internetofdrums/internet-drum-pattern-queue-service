package com.internetofdrums.api.queue.service.api;

import java.util.concurrent.BlockingQueue;

public interface DistinctBlockingQueue<E> extends BlockingQueue<E> {

    boolean offerUnique(E e) throws QueueException;
}
