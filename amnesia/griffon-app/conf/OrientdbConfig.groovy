database {
    username = 'guest'
    password = 'guest'
    type     = 'document' // accepted values are 'document', 'object'
}
environments {
    development {
        database {
            url = 'local:/orient/databases/amnesia-dev'
        }
    }
    test {
        database {
            url = 'local:/orient/databases/amnesia-test'
        }
    }
    production {
        database {
            url = 'local:/orient/databases/amnesia-prod'
        }
    }
}
