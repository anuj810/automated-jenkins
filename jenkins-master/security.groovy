#!groovy

import jenkins.model.*
import hudson.security.*
import hudson.plugins.active_directory.*
import jenkins.security.s2m.AdminWhitelistRule

def instance = Jenkins.getInstance()

String domain = 'ad.local'
String site = ''
String server = '10.120.0.192:389, 10.120.18.63:389'
String bindName = 'Admin'
String bindPassword = 'Password@123'
def hudsonActiveDirectoryRealm = new ActiveDirectorySecurityRealm(domain, site, bindName, bindPassword, server)
instance.setSecurityRealm(hudsonActiveDirectoryRealm)

def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

println "--> Enable Agent → Master Access Control"
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class)
.setMasterKillSwitch(false);
instance.save()
