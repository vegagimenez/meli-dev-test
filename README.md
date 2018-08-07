# meli-dev-test
Hail to the Magneto team! 

PRE-FLIGHT CHECK: You need this apps installed in your environment: JSDK, Maven, Git (but if you're seeing this...)

This project with allow us to improve our verification time of dna chains, to do that, you will need to run this command:

mvn spring-boot:run -Dspring.profiles.active=local

NOTICE: The mentioned command need some previous configuration, like a mysql DB instance change the values of this parameters:
jdbcUrl
jdbcUser
jdbcPass

in the file application-local.yaml to avoid errors.

CAUTION: This version it's not finished, it's only a little probe of the spring-boot + parallelization power I'm in the middle of the configuration 
of an AMI in AWS with an auto scaling group and a ELB in front to take over hundreds or millions of requests, but, because our cause ain't $upported
by anyone, I have to be very careful with this to avoid a surprising invoice in September ;)

If everything run as expected, you will be able to check some dna chains on this uri:
http://localhost:8080/mutant/

Enjoy! XD

