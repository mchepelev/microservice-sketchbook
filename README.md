### Java async code usage examples

In core service you could find examples of async java using:
1. Java core
2. ProjectReactor

To run code use tests. 
Tests used only to run code, not to check it (always green). 
Each test waits for 60 sec to give async processes time to finish


How to start:
1. run cloud-config
2. run eureka
3. run tests from core-service

### Java reactive repository alongside with jpa and jdbc repositories

In core service

Unit tests using real db. (create db using docker compose and generate-*.sql files)

*Didn't find way to start using bootstrap config. See details below*

When R2JDBC is used in project JPA support removed by default (see DataSourceAutoConfiguration.class)

Some resources (e.g. spring boot reference guide) suggest to add 
**@Import(DataSourceAutoConfiguration.class)** to Config class

But this didn't work for me

The only solution that worked for me is **Java based config** for JPA and JDBC. 
R2DBC could be configured using yml or java class. For consistency java based config is used

JPA and R2DBC couldn't use same entities. Entities look pretty similar, but use different annotations.