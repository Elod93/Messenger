package org.messenger.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTopic is a Querydsl query type for Topic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTopic extends EntityPathBase<Topic> {

    private static final long serialVersionUID = 2024317989L;

    public static final QTopic topic = new QTopic("topic");

    public final StringPath category_name = createString("category_name");

    public final ListPath<Message, QMessage> messages = this.<Message, QMessage>createList("messages", Message.class, QMessage.class, PathInits.DIRECT2);

    public final NumberPath<Integer> topicId = createNumber("topicId", Integer.class);

    public QTopic(String variable) {
        super(Topic.class, forVariable(variable));
    }

    public QTopic(Path<? extends Topic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTopic(PathMetadata metadata) {
        super(Topic.class, metadata);
    }

}

