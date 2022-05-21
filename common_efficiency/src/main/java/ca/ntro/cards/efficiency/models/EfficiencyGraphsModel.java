package ca.ntro.cards.efficiency.models;

import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyObject2d;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyWorld2d;

public abstract class   EfficiencyGraphsModel<GRAPHS_MODEL extends EfficiencyGraphsModel, 
                                              OBJECT2D     extends EfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                              WORLD2D      extends EfficiencyWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                              OPTIONS      extends EfficiencyDrawingOptions,
                                              VIEW_DATA    extends EfficiencyViewData<OBJECT2D, WORLD2D, OPTIONS>>

                extends CommonCanvasModel<GRAPHS_MODEL,
                                          OBJECT2D,
                                          WORLD2D,
                                          OPTIONS,
                                          VIEW_DATA> {

}
