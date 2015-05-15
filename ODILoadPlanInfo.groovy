//Created by Ravi Sankar Krothapalli


import oracle.odi.domain.runtime.loadplan.OdiLoadPlanStep;
import oracle.odi.domain.runtime.loadplan.OdiLoadPlan;
import oracle.odi.domain.runtime.loadplan.finder.IOdiLoadPlanFinder;



//Read input with odiInputStream variable
odiInputStream.withReader { reader ->
  while (true) {
    getSessionInformation(reader.readLine());
    break;
  }
}



def getSessionInformation(nameLoadPlan){

    def scenarios = []

    curLoadPlan = ((IOdiLoadPlanFinder)odiInstance.getTransactionalEntityManager().getFinder(OdiLoadPlan.class)).findByName(nameLoadPlan);
    childloop = {parent -> parent.getChildrenSteps().each({it -> 
                           if (it.getClass().getName().matches('oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepRunScenario')) scenarios.add(it); 
                           if (!it.getClass().getName().matches('oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepRunScenario|oracle.odi.domain.runtime.loadplan.OdiLoadPlanStepCase')) childloop(it)})};
    childloop(curLoadPlan.getRootStep()); 
    //scenarios.each({println it.getName()});
    selScenarios = scenarios.findAll({scenario -> !scenario.getName().contains(' : ') && !scenario.getName().contains(' ') });
    selScenarios.each({println it.getScenarioTag()});
}

