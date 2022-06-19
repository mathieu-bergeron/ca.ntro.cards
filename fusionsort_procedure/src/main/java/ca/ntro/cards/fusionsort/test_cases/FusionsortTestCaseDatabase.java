// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureDashboardModel;
import ca.ntro.cards.fusionsort.models.values.FusionsortTestCase;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTrace;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   FusionsortTestCaseDatabase<STUDENT_MODEL extends TriFusion> 

       extends ProcedureTestCaseDatabase<TriFusion, 
                                         STUDENT_MODEL, 
                                         FusionsortTestCase, 
                                         FusionsortExecutionTrace,
                                         FusionsortProcedureDashboardModel> {

    @Override
    public void describeTestCasesToGenerate() {
        
        AbstractTestCaseDescriptor descriptor = AbstractTestCaseDescriptor.create()
                                                          .category("exemples")
                                                          .testCaseId("ex01");
        
        addTestCase(descriptor);
        
        AbstractTestCaseDescriptor descriptor2 = AbstractTestCaseDescriptor.create()
											              .category("exemples")
											              .testCaseId("ex02");

        addTestCase(descriptor2);

        AbstractTestCaseDescriptor descriptor3 = AbstractTestCaseDescriptor.create()
														  .category("exemples")
														  .testCaseId("ex03");

		addTestCase(descriptor3);

    }

}
