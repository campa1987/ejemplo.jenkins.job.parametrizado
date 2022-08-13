job('Ejemplo-job-DSL') {
   description('Job DSL de ejemplo para el curso de Jenkins')
   scm{
     git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
       node / gitConfigName('FernandoCampagna')
       node / gitConfigEmail('fernandocampagna01@gmail.com')
     }
   }  
   parameters {
        stringParam('nombre', defaultValue= 'Fernando', description = 'Parametro de cadanea para el job Booleano') 
        choiceParam('Planeta', ['Mercurio (default)', 'Marte', 'Jupiter','Saturno','Neptuno','Urano'])
        booleanParam('Agente', false)
   }
   triggers {
        cron('H/7 * * * *' )
   }
  steps {
        shell("bash jobscript.sh")
  }
  publishers {
 		mailer('fernandocampagna01@gmail.com', true, true)   
        slackNotifier {
      		notifyAborted(true)
      		notifyEveryFailure(true)
      		notifyNotBuilt(false)
      		notifyUnstable(false)
      		notifyBackToNormal(true)
      		notifySuccess(false)
      		notifyRepeatedFailure(false)
      		startNotification(false)
      		includeTestSummary(false)
      		includeCustomMessage(false)
      		customMessage(null)
      		sendAs(null)
      		commitInfoChoice('NONE')
      		teamDomain(null)
      		authToken(null)
    	}    
  }
}
