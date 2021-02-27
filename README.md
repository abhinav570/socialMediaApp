# socialMediaApp
For demo purpose

swagger link:
http://localhost:8082/swagger-ui.html#/social-media-controller

h2-console link:
http://localhost:8082/h2-console/login.jsp
user name: sa
password: 

sample queries:
insert into user values('user1','userName1');
insert into user values('user2','userName2');
insert into user values('user3','userName3');
insert into follow(follower_id, followee_id) values('user1','user2');
insert into follow(follower_id, followee_id) values('user1','user3');
insert into follow(follower_id, followee_id) values('user2','user1');
insert into follow(follower_id, followee_id) values('user3','user1');