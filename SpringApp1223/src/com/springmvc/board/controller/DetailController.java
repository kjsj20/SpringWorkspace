package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.domain.Board;
import com.model2.model.BoardDAO;

public class DetailController implements Controller{
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		Board board = boardDAO.select(board_id);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board", board);
		
		mav.setViewName("/board/detail");
		
		return mav;
	}
	
}
