#!groovy

import jenkins.model.*
import hudson.security.*
import hudson.plugins.active_directory.*
import jenkins.security.s2m.AdminWhitelistRule

def instance = Jenkins.getInstance()

println "--> creating local user 'admin'"

def domains = new ArrayList<ActiveDirectoryDomain>();
def securityRealm = new ActiveDirectorySecurityRealm(
"ad.local",
domains,
"",
"Admin",
"{AQAAABAAAAAQIYYA1hwjj+EoKn/1NmTC5f8kOJKoxA8G19TK2/G0Oxg=}}",
"10.120.0.192:389",
GroupLookupStrategy.RECURSIVE,
false,
true,
null)
instance.setSecurityRealm(securityRealm)

def strategy = new
hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

println "--> Enable Agent → Master Access Control"
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class)
.setMasterKillSwitch(false);
instance.save()
