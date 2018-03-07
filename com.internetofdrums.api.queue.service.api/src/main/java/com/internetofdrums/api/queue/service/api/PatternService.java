package com.internetofdrums.api.queue.service.api;

import java.util.List;
import java.util.Optional;

public interface PatternService extends Service {

    String BASE_PATH = "/patterns";

    String QUEUE_PATH = BASE_PATH;
    String LIST_QUEUE_PATH = QUEUE_PATH;
    String OFFER_TO_QUEUE_PATH = QUEUE_PATH;

    String HEAD_OF_QUEUE_PATH = "/head";
    String GET_HEAD_OF_QUEUE_PATH = BASE_PATH + HEAD_OF_QUEUE_PATH;
    String GET_AND_REMOVE_HEAD_OF_QUEUE_PATH = BASE_PATH + HEAD_OF_QUEUE_PATH;

    List<ListedDrumPattern> listQueue();

    boolean offerToQueue(NewDrumPattern newDrumPattern);

    Optional<DetailedDrumPattern> getHeadOfQueue();

    Optional<DetailedDrumPattern> getAndRemoveHeadOfQueue();
}
