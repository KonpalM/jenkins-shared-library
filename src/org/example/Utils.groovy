package org.example

class Utils {
    def steps

    Utils() {
        this.steps = org.jenkinsci.plugins.workflow.cps.CpsScript.current()
    }

    def notify(String message) {
        steps.echo "Notification: ${message}"
    }
}
