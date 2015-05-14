//Created by Ravi Sankar Krothapalli

import oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepRunScenario;
import oracle.odi.domain.runtime.loadplan.OdiLoadPlanStep;
import oracle.odi.domain.runtime.loadplan.OdiLoadPlan;
import oracle.odi.domain.runtime.lpi.OdiLoadPlanInstance;
import oracle.odi.domain.runtime.loadplan.finder.IOdiLoadPlanFinder;
import oracle.odi.domain.runtime.lpi.finder.IOdiLoadPlanInstanceFinder;
import oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepSerial;
import oracle.odi.domain.runtime.lpi.OdiLoadPlanInstanceRun;


//Read input with odiInputStream variable
odiInputStream.withReader { reader ->
  while (true) {
    getSessionInformation(reader.readLine());
    break;
  }
}



def getSessionInformation(nameLoadPlan){
    scenarioclass = 'oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepRunScenario'
    stepcaseclass = 'oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepCase'
    curLoadPlan = ((IOdiLoadPlanFinder)odiInstance.getTransactionalEntityManager().getFinder(OdiLoadPlan.class)).findByName(nameLoadPlan);
    childloop = {parent -> parent.getChildrenSteps().each({it -> 
                           if (it.getClass().getName().matches('oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepRunScenario')) println it; 
                           if (!it.getClass().getName().matches('oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepRunScenario|oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepCase')) childloop(it)})};
    childloop(curLoadPlan.getRootStep()); 
    println childloop.each({println it});
}

