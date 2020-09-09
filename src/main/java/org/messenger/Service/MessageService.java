package org.messenger.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.messenger.SessionBean.UserInfo;
import org.messenger.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class MessageService {
    @PersistenceContext
    EntityManager em;

    public Set<String> nameCounter =new HashSet<>();

    @Transactional
    public void createMessage(Message message) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Object principal=authentication.getPrincipal();

        if (principal instanceof OidcUser) {
            OidcUser oidcUser= (OidcUser) principal;
            message.setName(oidcUser.getFamilyName());
            message.setDate(LocalDateTime.now());
        }
         else if(principal instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) principal;
            message.setName(securityUser.getUsername());
            message.setDate(LocalDateTime.now());
        }
        em.persist(message);
    }
    @Transactional
    public void  addNewTopic(Topic topic){
        em.persist(topic);
    }

    public List<Topic> allTopic(){
        return em.createQuery("SELECT t FROM Topic t",Topic.class).getResultList();
    }


//
//public List<Message> messageHandlerWithJpqlUser(int limit, String order_by, String dest,Integer topicId,Boolean deleted) {
//    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//    QMessage message=QMessage.message;
//    QTopic topic=QTopic.topic;
//    BooleanBuilder booleanBuilder=new BooleanBuilder();
//    if(topicId!=0){
//        booleanBuilder.and(message.topic.topicId.eq(topicId));
//    }
//    return queryFactory.selectFrom(message).join(message.topic,topic).where(booleanBuilder)
//            .orderBy(orderSpecifier(dest,orderBySelect(order_by)))
//            .limit(limit)
//            .fetch();
//}
//public List<Message> messageHandlerWithJpqlAdmin(int limit, String order_by, String dest,Boolean deleted,Integer topicId,String dateFrom,String dateTo) {
//    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//    QMessage message=QMessage.message;
//    QTopic topic=QTopic.topic;
//    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd\'T\'HH:mm");
//    BooleanBuilder booleanBuilder=new BooleanBuilder();
//    if(topicId!=0){
//        booleanBuilder.and(message.topic.topicId.eq(topicId));
//    }
//    if(deleted!=null){
//        booleanBuilder.and(message.deleted.eq(deleted));
//    }
//    if(StringUtils.isNotBlank(dateFrom)){
//        booleanBuilder.and(message.date.after(LocalDateTime.parse(dateFrom,formatter)));
//    }
//    if(StringUtils.isNotBlank(dateTo)){
//        booleanBuilder.and(message.date.before(LocalDateTime.parse(dateTo,formatter)));
//    }
//   return queryFactory.selectFrom(message).join(message.topic,topic).where(booleanBuilder)
//            .orderBy(orderSpecifier(dest,orderBySelect(order_by)))
//            .limit(limit)
//            .fetch();
//
//}

    @Transactional
    public Message oneMessage(int userId) {
    return em.createQuery("SELECT m FROM Message m",Message.class).getResultList().stream()
                .filter(message1 -> message1.getUserId() == userId)
                .collect(Collectors.toList()).get(0);
    }
    public void userNameController(UserInfo userInfo){
     if (userInfo.getName() !=null){
         nameCounter.add(userInfo.getName());
     }
   userInfo.setNameCounter(nameCounter.size());
    }

    @Transactional
    public void setDeleted(int userId){
        em.createQuery("SELECT m from Message m",Message.class).getResultList().stream()
                .filter(message -> message.getUserId()==userId)
                .findFirst().ifPresent(message -> message.setDeleted(true));
    }

//    public ComparableExpressionBase orderBySelect(String order){
//        if(order.equals("name")){
//            return QMessage.message.name;
//        }
//        else if(order.equals("date")){
//            return QMessage.message.date;
//        }
//        return null;
//    }
//    private OrderSpecifier<?> orderSpecifier(String order,ComparableExpressionBase comparableExpressionBase){
//        if(order.equals("desc")){
//            return comparableExpressionBase.desc();
//
//        }else{
//            return comparableExpressionBase.asc();
//        }
//    }
    public List<Message> findAllMessages(){
      return   em.createQuery("SELECT m from Message m",Message.class).getResultList();
    }
    @Transactional
    public Message createMessageList(Message message) {
        message.setName("ElBa");
        message.setDate(LocalDateTime.now());
        em.persist(message);
        return message;
    }
    @Transactional
    public void deleteById(Long id){
        em.createQuery("SELECT m FROM Message m WHERE m.userId=:id",Message.class).setParameter("id",id).getSingleResult().setDeleted(false);
        }
}
