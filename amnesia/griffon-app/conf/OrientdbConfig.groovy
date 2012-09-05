database {
    username = 'admin'
    password = 'admin'
    type     = 'document' // accepted values are 'document', 'object'

}
environments {
    development {
        database {
            url = 'local:/home/lucas/projetos/amnesia/amnesia-dev'
        }
    }
    test {
        database {
            url = 'local:/home/lucas/projetos/amnesia/amnesia-test'
        }
    }
    production {
        database {
            url = 'local:/home/lucas/projetos/amnesia/amnesia-prod'
        }
    }
}
