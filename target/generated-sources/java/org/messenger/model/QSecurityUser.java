package org.messenger.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSecurityUser is a Querydsl query type for SecurityUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSecurityUser extends EntityPathBase<SecurityUser> {

    private static final long serialVersionUID = -2032156811L;

    public static final QSecurityUser securityUser = new QSecurityUser("securityUser");

    public final SetPath<Authority, QAuthority> authorities = this.<Authority, QAuthority>createSet("authorities", Authority.class, QAuthority.class, PathInits.DIRECT2);

    public final StringPath dateOfBirth = createString("dateOfBirth");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> loginDate = createDateTime("loginDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath role = createString("role");

    public QSecurityUser(String variable) {
        super(SecurityUser.class, forVariable(variable));
    }

    public QSecurityUser(Path<? extends SecurityUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSecurityUser(PathMetadata metadata) {
        super(SecurityUser.class, metadata);
    }

}

