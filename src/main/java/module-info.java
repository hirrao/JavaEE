module org.health{
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires lombok;
    requires jjwt.api;

    opens org.health;
}