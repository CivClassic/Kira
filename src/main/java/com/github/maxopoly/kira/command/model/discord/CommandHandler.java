package com.github.maxopoly.kira.command.model.discord;

import com.github.maxopoly.kira.KiraMain;
import com.github.maxopoly.kira.command.discord.admin.CreateDefaultPermsCommand;
import com.github.maxopoly.kira.command.discord.admin.DeauthDiscordCommand;
import com.github.maxopoly.kira.command.discord.admin.GiveDefaultPermission;
import com.github.maxopoly.kira.command.discord.admin.GivePermissionToRoleCommand;
import com.github.maxopoly.kira.command.discord.admin.GiveRoleCommand;
import com.github.maxopoly.kira.command.discord.admin.LeaveDiscordServerCommand;
import com.github.maxopoly.kira.command.discord.admin.ListDiscordRelaysCommand;
import com.github.maxopoly.kira.command.discord.admin.ListDiscordServersCommand;
import com.github.maxopoly.kira.command.discord.admin.ListPermissionsForUserCommand;
import com.github.maxopoly.kira.command.discord.admin.ManageDiscordBansCommand;
import com.github.maxopoly.kira.command.discord.admin.ReloadPermissionCommand;
import com.github.maxopoly.kira.command.discord.admin.StopCommand;
import com.github.maxopoly.kira.command.discord.admin.SyncUsernameCommand;
import com.github.maxopoly.kira.command.discord.api.GenerateAPIToken;
import com.github.maxopoly.kira.command.discord.api.ListTokens;
import com.github.maxopoly.kira.command.discord.api.RevokeAPIToken;
import com.github.maxopoly.kira.command.discord.ingame.ConsoleCommand;
import com.github.maxopoly.kira.command.discord.ingame.RunIngameCommand;
import com.github.maxopoly.kira.command.discord.relay.ConfigureRelayConfigCommand;
import com.github.maxopoly.kira.command.discord.relay.CreateRelayChannelHereCommand;
import com.github.maxopoly.kira.command.discord.relay.CreateRelayConfig;
import com.github.maxopoly.kira.command.discord.relay.DeleteRelayCommand;
import com.github.maxopoly.kira.command.discord.relay.TieRelayConfigCommand;
import com.github.maxopoly.kira.command.discord.user.AuthCommand;
import com.github.maxopoly.kira.command.discord.user.ChannelInfoCommand;
import com.github.maxopoly.kira.command.discord.user.GetWeightCommand;
import com.github.maxopoly.kira.command.discord.user.HelpCommand;
import com.github.maxopoly.kira.command.discord.user.InfoCommand;
import com.github.maxopoly.kira.command.discord.user.JoinDiscordCommand;
import com.github.maxopoly.kira.command.discord.user.QuoteCommand;
import com.github.maxopoly.kira.command.discord.user.SelfInfoCommand;
import com.github.maxopoly.kira.command.model.top.InputSupplier;
import com.github.maxopoly.kira.command.model.top.TextInputHandler;
import org.apache.logging.log4j.Logger;

public class CommandHandler extends TextInputHandler<Command, String, InputSupplier> {

	public CommandHandler(Logger logger) {
		super(logger);
	}

	@Override
	protected String convertIntoArgument(String raw) {
		return raw;
	}

	@Override
	protected String getCommandArguments(String fullArgument) {
		int index =  fullArgument.indexOf(' ');
		if (index == -1) {
			return "";
		}
		return fullArgument.substring(index + 1, fullArgument.length());
	}

	@Override
	protected String getCommandIdentifier(String argument) {
		int index =  argument.indexOf(' ');
		if (index == -1) {
			return argument;
		}
		return argument.substring(0, index);
	}

	@Override
	protected String getHandlerName() {
		return "Discord Command Handler";
	}

	@Override
	protected void handleError(InputSupplier supplier, String input) {
		supplier.reportBack("Invalid command");
	}

	@Override
	public void registerCommand(Command command) {
		if (command.getRequiredPermission() != null) {
			KiraMain.getInstance().getKiraRoleManager().getOrCreatePermission(command.getRequiredPermission());
		}
		super.registerCommand(command);
	}

	@Override
	protected void registerCommands() {
		// Admin
		registerCommand(new CreateDefaultPermsCommand());
		registerCommand(new DeauthDiscordCommand());
		registerCommand(new GiveDefaultPermission());
		registerCommand(new GivePermissionToRoleCommand());
		registerCommand(new GiveRoleCommand());
		registerCommand(new LeaveDiscordServerCommand());
		registerCommand(new ListDiscordRelaysCommand());
		registerCommand(new ListDiscordServersCommand());
		registerCommand(new ListPermissionsForUserCommand());
		registerCommand(new ManageDiscordBansCommand());
		registerCommand(new ReloadPermissionCommand());
		registerCommand(new StopCommand());
		registerCommand(new SyncUsernameCommand());
		// API
		registerCommand(new GenerateAPIToken());
		registerCommand(new ListTokens());
		registerCommand(new RevokeAPIToken());
		// Game
		registerCommand(new ConsoleCommand());
		registerCommand(new RunIngameCommand());
		// Relay
		registerCommand(new ConfigureRelayConfigCommand());
		registerCommand(new CreateRelayChannelHereCommand());
		registerCommand(new CreateRelayConfig());
		registerCommand(new DeleteRelayCommand());
		registerCommand(new TieRelayConfigCommand());
		// User
		registerCommand(new AuthCommand());
		registerCommand(new ChannelInfoCommand());
		registerCommand(new GetWeightCommand());
		registerCommand(new HelpCommand());
		registerCommand(new InfoCommand());
		registerCommand(new JoinDiscordCommand());
		registerCommand(new QuoteCommand());
		registerCommand(new SelfInfoCommand());
		logger.info("Loaded total of " + commands.values().size() + " commands");
	}

}
