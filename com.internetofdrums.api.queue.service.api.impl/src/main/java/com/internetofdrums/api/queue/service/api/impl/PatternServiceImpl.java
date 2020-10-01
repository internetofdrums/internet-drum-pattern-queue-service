package com.internetofdrums.api.queue.service.api.impl;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.DistinctBlockingQueue;
import com.internetofdrums.api.queue.service.api.ListedDrumPattern;
import com.internetofdrums.api.queue.service.api.NewDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.queue.service.api.QueueException;
import com.internetofdrums.api.queue.service.api.impl.entity.DetailedDrumPatternEntity;
import com.internetofdrums.api.queue.service.api.impl.entity.ListedDrumPatternEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatternServiceImpl implements PatternService {

    private static final int MAXIMUM_NUMBER_OF_PATTERNS_IN_QUEUE = 2;

    private final DistinctBlockingQueue<DetailedDrumPatternEntity> queue = new FairDistinctBlockingQueueImpl<>(MAXIMUM_NUMBER_OF_PATTERNS_IN_QUEUE);

    @Override
    public List<ListedDrumPattern> listQueue() {
        return Arrays.stream(queue.toArray(new DetailedDrumPatternEntity[0]))
                .map(ListedDrumPatternEntity::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public boolean offerToQueue(NewDrumPattern newDrumPattern) throws QueueException {
        return queue.offerUnique(DetailedDrumPatternEntity.valueOf(newDrumPattern));
    }

    @Override
    public Optional<DetailedDrumPattern> getHeadOfQueue() {
        return Optional.ofNullable(queue.peek());
    }

    @Override
    public Optional<String> getHeadOfQueuePattern() {
        DetailedDrumPatternEntity head = queue.peek();

        if (head == null) {
            return Optional.empty();
        }

        return Optional.of(head.getPattern());
    }

    @Override
    public Optional<DetailedDrumPattern> getAndRemoveHeadOfQueue() {
        return Optional.ofNullable(queue.poll());
    }

    @Override
    public Optional<String> getPatternAndRemoveHeadOfQueue() {
        DetailedDrumPatternEntity head = queue.poll();

        if (head == null) {
            return Optional.empty();
        }

        return Optional.of(head.getPattern());
    }
}
