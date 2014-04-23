import org.apache.log4j.Appender
import org.apache.log4j.ConsoleAppender
import org.apache.log4j.FileAppender
import org.apache.log4j.Level
import org.apache.log4j.PatternLayout
import org.apache.log4j.RollingFileAppender
import org.apache.log4j.varia.NullAppender

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// log4j configuration
log4j = {
    // Configure appenders based on the environment
    //
    // We will end up with 3 appenders:
    //
    // * Console
    // * Debug file
    // * Standard log file
    //
    // Depending on the environment, some of these will be null appenders.

    // Set up our conversion pattern
    // For reference, stock is '%d %-5p [%c{1}] %m%n'
    def pattern = new PatternLayout('%d %X{appVersion} %-5p %X{requestId} [%c{1}] %X{context} %m%n')

    // Console appender
    Appender consoleAppender

    // Log file appender
    Appender logFileAppender

    // Debug file appender
    Appender debugLogFileAppender

    // Time-based rolling file interval
    // This string sets the rotation schedule to weekly
    String dailyRollPattern = "'.'yyyy-ww"

    // Set up the appenders per environment
    environments {
        development {
            // Console
            consoleAppender = new ConsoleAppender(name: 'stdout', threshold: Level.ALL, layout: pattern)

            // Log file
            logFileAppender = new NullAppender(name: 'logFile')

            // Debug log file
            debugLogFileAppender = new FileAppender(
                    name: 'debugLogFile',
                    fileName: (System.getProperty('catalina.base') ?: 'target') + "/logs/${appName}-debug.log",
                    threshold: Level.TRACE,
                    layout: pattern
            )
        }
        staging {
            // Console
            consoleAppender = new ConsoleAppender(name: 'stdout', threshold: Level.FATAL)

            // Log file
            logFileAppender = new RollingFileAppender(
                    name: 'logFile',
                    fileName: (System.getProperty('catalina.base') ?: 'target') + "/logs/${appName}.log",
                    threshold: Level.INFO,
                    maxFileSize: 500 * 1024 * 1024,
                    maxBackupIndex: 2,
                    layout: pattern
            )

            // Debug log file
            debugLogFileAppender = new RollingFileAppender(
                    name: 'debugLogFile',
                    fileName: (System.getProperty('catalina.base') ?: 'target') + "/logs/${appName}-debug.log",
                    threshold: Level.TRACE,
                    maxFileSize: 500 * 1024 * 1024,
                    maxBackupIndex: 2,
                    layout: pattern
            )
        }
        production {
            // Console
            consoleAppender = new ConsoleAppender(name: 'stdout', threshold: Level.FATAL)

            // Log file
            logFileAppender = new RollingFileAppender(
                    name: 'logFile',
                    fileName: (System.getProperty('catalina.base') ?: 'target') + "/logs/${appName}.log",
                    threshold: Level.INFO,
                    maxFileSize: 500 * 1024 * 1024,
                    maxBackupIndex: 2,
                    layout: pattern
            )

            // Debug log file
            debugLogFileAppender = new RollingFileAppender(
                    name: 'debugLogFile',
                    fileName: (System.getProperty('catalina.base') ?: 'target') + "/logs/${appName}-debug.log",
                    threshold: Level.TRACE,
                    maxFileSize: 500 * 1024 * 1024,
                    maxBackupIndex: 2,
                    layout: pattern
            )
        }
    }

    // Add the appenders
    appenders {
        // Disable the stacktrace log
        appender new NullAppender(name: 'stacktrace')

        // Console appender
        appender consoleAppender

        // Log file appender
        appender logFileAppender

        // Debug log appender
        appender debugLogFileAppender
    }

    // Configure the root logger
    // We set the root logger threshold to ERROR so that we don't
    // automatically get every log message under the sun.
    root {
        error 'stdout', 'logFile', 'debugLogFile'
        additivity = true
    }

    // These packages are straight from the unmodified default grails log4j config
    error   'org.codehaus.groovy.grails.web.servlet', // controllers
            'org.codehaus.groovy.grails.web.pages', // GSP
            'org.codehaus.groovy.grails.web.sitemesh', // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.web.context',
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.codehaus.groovy.grails.scaffolding',
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate',
            'grails.util.GrailsUtil' // Disable deprecated warnings on ApplicationHolder and ConfigurationHolder

    // These existed in the previous config
    warn    'grails.spring',
            'org.apache.catalina',
            'org.apache.coyote',
            'org.apache.commons.digester'

    // These also existed in the previous config
    info    'grails',
            'httpclient.wire',
            'org.apache.axis.transport',
            'javax',
            'com.sun.jersey'

    // Enable all logging from our code
    // Even though we're allowing all log levels, the threshold set at
    // the appender level is honored.
    all     'com.toastcoders',
            'grails.app.controllers.com.toastcoders',
            'grails.app.domain.com.toastcoders',
            'grails.app.services.com.toastcoders',
            'grails.app.taglib.com.toastcoders',
            'grails.app.conf.com.toastcoders',
            'grails.app.filters.com.toastcoders',
            'Stacktrace'
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.toastcoders.gnatspray.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.toastcoders.gnatspray.UserRole'
grails.plugin.springsecurity.authority.className = 'com.toastcoders.gnatspray.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll']
]

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.roleHierarchy = '''
    ROLE_ADMIN > ROLE_USER
'''