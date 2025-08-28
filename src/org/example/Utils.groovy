package org.example   // defines the package name (like a namespace)

class Utils {
    def steps   // this will hold Jenkins "steps" like echo, sh, etc.

    Utils(steps) {   // constructor → called when we create new Utils(this)
        this.steps = steps
    }

    def notify(String message) {   // function
        steps.echo "🔔 Notification: ${message}"
    }
}
