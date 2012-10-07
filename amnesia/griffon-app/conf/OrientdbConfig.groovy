import org.slf4j.Logger
import org.slf4j.LoggerFactory
def log = LoggerFactory.getLogger("Initialize.groovy")

def slurper = new ConfigSlurper(Environment.current.name)

def conf = slurper.parse(new File("../configuration.groovy").toURL())
location = conf.database.location

log.info(location)

database {
    username = 'admin'
    password = 'admin'
    type     = 'object' // accepted values are 'document', 'object'

}
environments {
    development {
        database {
            url = "local:${location}"
        }
    }
    test {
        database {
            url = "local:${location}"
        }
    }
    production {
        database {
            url = "local:${location}"
        }
    }
}
