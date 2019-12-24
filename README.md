Test accounts credentials:
--------------------------
user role:
    email: user@gmail.com
    pass: user
    
admin role:
    email: admin@admin.com
    pass: admin

To start application:
---------------------
0) Start mongo db: 
docker run -d -p 27017-27019:27017-27019 mongo
1) mvn clean package
2) java -jar target/quiz.jar OR just run QuizApplication in IDE
3) go to http://localhost:8080 in browser
4) to open swagger - visit: 
http://localhost:8080/swagger-ui.html/
5) To check the results go to http://localhost:8080/results


To start only react app:
1) cd src/main/ui
2) yarn run start
3) go to http://localhost:3000