package com.game.common.server.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.common.server.GameServer;
import com.game.common.server.manager.GameSessionManager;
import com.game.common.server.session.GameSession;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangjp
 *
 */
public class GameServerHandler extends SimpleChannelInboundHandler<GameObject.GamePbObject> {
	private static final Logger logger = LogManager.getLogger(GameServerHandler.class);
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GamePbObject msg) throws Exception {
		// TODO Auto-generated method stub
		GameSession session=GameSessionManager.getInstance().getSession(ctx.channel());
		if(session==null){
			throw new GameException("session is null");
		}
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		GameSession session=new GameSession(ctx.channel());
		GameSessionManager.getInstance().addSession(session);
		logger.info("session create id"+session.getId()+"   ip:"+ctx.channel().remoteAddress());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
	}

}
