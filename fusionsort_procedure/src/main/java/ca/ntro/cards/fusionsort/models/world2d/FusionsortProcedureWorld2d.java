package ca.ntro.cards.fusionsort.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.fusionsort.FusionsortConstants;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class FusionsortProcedureWorld2d extends ProcedureWorld2d<FusionsortProcedureObject2d, FusionsortProcedureWorld2d, FusionsortProcedureDrawingOptions> {
    
    private ProcedureMsgAcceptManualModel msgManualExecutionStep = NtroApp.newMessage(ProcedureMsgAcceptManualModel.class);
    
    @Override
    public void buildAndSendManualModel() {

        // TODO: analyser les cartes2d existantes et créer un CardsModel 
        
        TriFusion manualModel = new TriFusion();
        
        msgManualExecutionStep.setManualModel(manualModel);
        msgManualExecutionStep.send();
    }


}
