package ca.ntro.cards.intro.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.intro.IntroConstants;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class IntroProcedureWorld2d extends ProcedureWorld2d<IntroProcedureWorld2d, IntroProcedureDrawingOptions> {
    
    private ProcedureMsgAcceptManualModel msgManualExecutionStep = NtroApp.newMessage(ProcedureMsgAcceptManualModel.class);
    
    @Override
    public void buildAndSendManualModel() {

        // TODO: analyser les cartes2d existantes et créer un CardsModel 
        
        Intro manualModel = new Intro();
        
        msgManualExecutionStep.setManualModel(manualModel);
        msgManualExecutionStep.send();
    }


}
