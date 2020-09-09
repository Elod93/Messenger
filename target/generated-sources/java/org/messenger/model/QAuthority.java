package org.messenger.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthority is a Querydsl query type for Authority
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuthority extends EntityPathBase<Authority> {

    private static final long serialVersionUID = 783643449L;

    public static final QAuthority authority = new QAuthority("authority");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roleName = createString("roleName");

    public final SetPath<SecurityUser, QSecurityUser> securityUsers = this.<SecurityUser, QSecurityUser>createSet("securityUsers", SecurityUser.class, QSecurityUser.class, PathInits.DIRECT2);

    public QAuthority(String variable) {
        super(Authority.class, forVariable(variable));
    }

    public QAuthority(Path<? extends Authority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthority(PathMetadata metadata) {
        super(Authority.class, metadata);
    }

}

