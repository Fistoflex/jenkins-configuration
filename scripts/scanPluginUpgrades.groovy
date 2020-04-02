// scan plugin updates

// Run this script on a Jenkins instance to scan the set of installed
// plugins for known security issues. For now, just report this to
// the console. TODO: integrate w/ JIRA to auto-ticket new security
// plugin updates.

import jenkins.security.UpdateSiteWarningsConfiguration

// Return a list of Security Warnings from the Jenkins update center
// that are pertinent to the plugins installed in this Jenkins instance
public Set getSecurityWarnings() {
    UpdateSiteWarningsConfiguration updates = new UpdateSiteWarningsConfiguration()
    Set warnings = updates.getApplicableWarnings()
    return warnings
}

public static String formatWarning(warning) {
    String formattedWarning = "Vulnerability detected in plugin: ${warning.component}. "
    formattedWarning += "${warning.message}. For more information, see ${warning.url}"
    return formattedWarning
}

public static void main(String[] args) {
    def warnings = getSecurityWarnings()
    def list = []
     warnings.each { warning ->
      //println(formatWarning(warning))
        list << warning
    }

   println(list)


def authString = "nadeem.shahzad@arbisoft.com:3aINhIFy5qzRHC9rrlYX0CF5"
def body_req = '''{
   "fields": {
     "project" : { "key" : "DEVOPS" },
     "issuetype" : { "name" : "Bug" },
     "summary" : "TEST",
     "description" : "TEST"}
 }'''


def jira_url = "https://arbisoft123.atlassian.net/rest/api/2/issue/"

      


def proc = [ "curl", "-u", "${authString}", "-X", "POST" ,"--data", "${body_req}", "-H", "Content-Type: application/json", "${jira_url}"].execute()


println(proc.text)

}
