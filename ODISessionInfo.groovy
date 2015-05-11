
//Created by Ravi Sankar Krothapalli
import oracle.odi.domain.runtime.session.OdiSession;
import oracle.odi.domain.runtime.session.finder.IOdiSessionFinder;

//Read input with odiInputStream variable
odiInputStream.withReader { reader ->
  while (true) {
    getSessionInformation(reader.readLine());
  }
}

def getSessionInformation(sessionID){
    
    //get the session instance
    curSession = ((IOdiSessionFinder)odiInstance.getTransactionalEntityManager().getFinder(OdiSession.class)).findBySessionId(sessionID.toInteger());
    println "Current Scenario : " + curSession.getSourceScenarioTag();
    curSessionStepLogs = curSession.getSessionStepLogs();
    println "Default Values of the session variables";
    
    //get default varibles with values
    curSession.getSessionVariables().each({println "\t" + it.getName() + " : " + it.getDefaultValue()}); 
    println "\n";
    
    //get session step task log info
    def cloSessionTaskLogs = {taskLog -> 
        println "\tTask Log :" + taskLog.getTaskName1() + " - " + taskLog.getTaskName2() + " - " + taskLog.getTaskName3()+ taskLog.getTaskName2()
        };
        
    //get session step log info     
    def cloSessionStepLogs = {stepLog -> 
        println "Step Log :" + stepLog.getName(); stepLog.getSessionTaskLogs().each({cloSessionTaskLogs.call(it)})
        };
        
    //loop through session steps    
    curSessionStepLogs.each({cStepLog -> 
        cloSessionStepLogs(cStepLog)
        });
}