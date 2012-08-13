database {
    username = 'admin'
    password = 'admin'
    type     = 'object' // accepted values are 'document', 'object'

}
environments {
    development {
        database {
            url = 'local:${basedir}../orientdb/amnesia-dev'
        }
    }
    test {
        database {
            url = 'local:${basedir}../orientdb/amnesia-test'
        }
    }
    production {
        database {
            url = 'local:/home/lucas/projects/amnesia/orientdb/amnesia-prod'
        }
    }
}
