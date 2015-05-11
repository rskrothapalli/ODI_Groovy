//Created by ODI Studio

import oracle.odi.domain.runtime.session.OdiSession;
import oracle.odi.domain.runtime.session.finder.IOdiSessionFinder;

curSession = ((IOdiSessionFinder)odiInstance.getTransactionalEntityManager().getFinder(OdiSession.class)).findBySessionId(21437500);
println "Current Scenario : " + curSession.getSourceScenarioTag();
curSessionStepLogs = curSession.getSessionStepLogs();
def cloSessionTaskLogs = {taskLog -> println "\tTask Log :" + taskLog.getTaskName1() + " - " + taskLog.getTaskName1() + " - " + taskLog.getTaskName2()+ taskLog.getTaskName2()};
def cloSessionStepLogs = {stepLog -> println "Step Log :" + stepLog.getName(); stepLog.getSessionTaskLogs().each({cloSessionTaskLogs.call(it)})};
curSessionStepLogs.each({cStepLog -> cloSessionStepLogs(cStepLog)});
