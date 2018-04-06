package com.internetofdrums.api.queue.service.api.impl;

import com.internetofdrums.api.queue.service.api.DistinctBlockingQueue;
import com.internetofdrums.api.queue.service.api.QueueException;

import java.util.concurrent.ArrayBlockingQueue;

class FairDistinctBlockingQueueImpl<E> extends ArrayBlockingQueue<E> implements DistinctBlockingQueue<E> {

    FairDistinctBlockingQueueImpl(int capacity) {
        super(capacity, true);
    }

    @Override
    public synchronized boolean offerUnique(E e) throws QueueException {
        if (super.contains(e)) {
            throw new QueueException("The element already exists in the queue.");
        }

        return super.offer(e);
    }
}
