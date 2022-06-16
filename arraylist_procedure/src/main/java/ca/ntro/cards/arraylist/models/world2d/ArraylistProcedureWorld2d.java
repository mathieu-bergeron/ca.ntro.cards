package ca.ntro.cards.arraylist.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.arraylist.ArraylistConstants;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class ArraylistProcedureWorld2d extends ProcedureWorld2d<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> {
    
    private ProcedureMsgAcceptManualModel msgManualExecutionStep = NtroApp.newMessage(ProcedureMsgAcceptManualModel.class);
    
    @Override
    public void buildAndSendManualModel() {

        // TODO: analyser les cartes2d existantes et créer un CardsModel 
        
        ListeTableau manualModel = new ListeTableau();
        
        List<ArraylistCard2d> cards = collectCardsExceptDraggedCard();

        /*
        if(draggedCard!=null) {
            addNullCardAtDraggedCardOriginalLocation();
        }
        */
        
        msgManualExecutionStep.setManualModel(manualModel);
        msgManualExecutionStep.send();
    }


}
