# SpringBootSecurityBasic
sample project to demonstrate spring security basic with JDBC auth and in memory auth

# Note

1. This contains example of how spring security can be configured using basic in memory authenticaction as well as jdbc authentication
2. For in memory authenticaction we do no required jdbc dependencies in pom.xml and spring by defualt generates login page for authetication as we have provided form() based authetication in our configuration file.
3. For JDBC authetication, here I have used H2 DB
4. To allow access to H2 DB, I have added that configuration in WebSecurityConfig file to not autheticate the URL for h2 console

# About code

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
}

Above is for getting handle for authetication manager builder which spring used for authetication.
Here we are autheticating user using username and password

@Override
protected void configure(HttpSecurity http) throws Exception {
}

Above is for authorization whether logged in user is allowed to accesss that perticuler URL or not
So here I have defined whether perticuler user has access to perticuler resourse in the system

