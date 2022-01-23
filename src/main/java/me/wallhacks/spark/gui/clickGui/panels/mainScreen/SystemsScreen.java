package me.wallhacks.spark.gui.clickGui.panels.mainScreen;

import me.wallhacks.spark.gui.clickGui.ClickGuiMenuBase;
import me.wallhacks.spark.gui.clickGui.ClickGuiPanel;
import me.wallhacks.spark.gui.panels.GuiPanelButton;
import net.minecraft.client.gui.Gui;
import me.wallhacks.spark.gui.clickGui.panels.mainScreen.moduleslist.GuiClientModulePanel;
import me.wallhacks.spark.gui.clickGui.panels.mainScreen.setting.GuiEditSettingPanel;
import me.wallhacks.spark.systems.clientsetting.clientsettings.AntiCheatConfig;
import me.wallhacks.spark.systems.clientsetting.clientsettings.ClientConfig;
import me.wallhacks.spark.systems.clientsetting.clientsettings.GuiSettings;

public class SystemsScreen extends ClickGuiPanel {


    @Override
    public void init() {
        super.init();


        guiModuleListPanel.moduleSearchField.setText("");
    }

    @Override
    public String getName() {
        return "Modules";
    }



    public SystemsScreen(ClickGuiMenuBase clickGuiMenuBase){
        super(clickGuiMenuBase);



        clientSettings = new GuiPanelButton[]{
                new GuiPanelButton(0,GuiSettings.getInstance().getName()),
                new GuiPanelButton(1,AntiCheatConfig.getInstance().getName()),
                new GuiPanelButton(2,ClientConfig.getInstance().getName()),

        };



    }
    @Override
    public void preformAction(GuiPanelButton button) {
        int id = button.getId();

        switch (id)
        {
            case 0: guiEditSettingPanel.setCurrentSettingsHolder(GuiSettings.getInstance()); break;
            case 1: guiEditSettingPanel.setCurrentSettingsHolder(AntiCheatConfig.getInstance()); break;
            case 2: guiEditSettingPanel.setCurrentSettingsHolder(ClientConfig.getInstance()); break;
        }
    }



    final GuiPanelButton[] clientSettings;


    public GuiEditSettingPanel guiEditSettingPanel = new GuiEditSettingPanel();

    public GuiClientModulePanel guiModuleListPanel = new GuiClientModulePanel(0,0,0,0);





    @Override
    public void renderContent(int MouseX, int MouseY, float deltaTime) {






        super.renderContent(MouseX,MouseY,deltaTime);






        int moduleListWidth = 200;
        int settingsWidth = 180;

        int height = 238;
        int width = moduleListWidth + settingsWidth + guiSettings.spacing;

        int x = getCenterX()-width/2;
        int y = getCenterY()-height/2;




        //module gui
        Gui.drawRect(x-4,y-4,x+width+4,y+height+4, guiSettings.getGuiMainPanelBackgroundColor().getRGB());



        guiModuleListPanel.setPositionAndSize(x,y,moduleListWidth,height);
        guiModuleListPanel.renderContent(MouseX,MouseY,deltaTime);


        guiEditSettingPanel.setPositionAndSize(x+moduleListWidth+ guiSettings.spacing,y,settingsWidth,height);
        guiEditSettingPanel.renderContent(MouseX,MouseY,deltaTime);


        y += height + 20;
        int widthbutton = (width + guiSettings.spacing - guiSettings.spacing * clientSettings.length) / clientSettings.length;

        Gui.drawRect(x-4,y-4,x+width+4,y+18+4, guiSettings.getGuiMainPanelBackgroundColor().getRGB());



        for (GuiPanelButton c : clientSettings) {
            c.setPositionAndSize(x,y,widthbutton,18);
            c.renderContent(MouseX,MouseY,deltaTime);

            x+=widthbutton+guiSettings.spacing;
        }

    }









}