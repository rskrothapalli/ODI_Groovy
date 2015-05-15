//Created by Ravi Sankar Krothapalli


import oracle.odi.domain.runtime.loadplan.OdiLoadPlanStep;
import oracle.odi.domain.runtime.loadplan.OdiLoadPlan;
import oracle.odi.domain.runtime.loadplan.finder.IOdiLoadPlanFinder;
import oracle.odi.domain.runtime.scenario.finder.IOdiScenarioFinder;
import oracle.odi.domain.runtime.scenario.OdiScenario;
import oracle.odi.domain.runtime.scenario.Tag


scenario = ((IOdiScenarioFinder)odiInstance.getTransactionalEntityManager().getFinder(OdiScenario.class)).findLatestByName('SDE_ORAR1213_ADAPTOR_SDE_ORA_DOMAINGENERAL_PAYROLLLEGISLATIVEDATAGROUP');
scnTag = scenario.getTag();

sceLoadPlan = ((IOdiLoadPlanFinder)odiInstance.getTransactionalEntityManager().getFinder(OdiLoadPlan.class)).findByScenarioUsed(scnTag);
sceLoadPlan.each({println it.getName()});