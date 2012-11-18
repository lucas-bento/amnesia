File relativeDatabaseDir  = new File("../database")
relativeDatabaseDir.mkdirs()

location = relativeDatabaseDir.absolutePath

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
