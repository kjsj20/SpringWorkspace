package com.study.springfinal.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.study.springfinal.common.FileManager;
import com.study.springfinal.domain.Gallery;
import com.study.springfinal.model.dao.GalleryDAO;

@Controller
@RequestMapping("/gallery")
public class GalleryController{
	@Autowired
	private GalleryDAO galleryDAO;
	//스프링 프레임웍은 업로드 컴포넌트로, apache fileupload를 사용함		
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(Gallery gallery, HttpServletRequest request) {
		
		MultipartFile photo = gallery.getPhoto();
		String newName = Long.toString(System.currentTimeMillis());
		String ext = FileManager.getExtend(photo.getOriginalFilename());
		String filename = newName+"."+ext;
		gallery.setFilename(filename);
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath("/data");
		System.out.println(realPath);
		try {
			photo.transferTo(new File(realPath + "/" + filename)); //dir경로
			
			int result = galleryDAO.insert(gallery);
			System.out.println(result);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/gallery/list";
	}
	
	//목록 가져오기 
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		List galleryList = galleryDAO.selectAll();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("galleryList", galleryList);
		mav.setViewName("gallery/list");
		
		return mav;
	}
	
	//상세보기 
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView select(int gallery_id) {
		Gallery gallery = galleryDAO.select(gallery_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("gallery", gallery);
		mav.setViewName("gallery/detail");
		return mav;
	}
	
	//수정
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(Gallery gallery) {
		galleryDAO.update(gallery);
		
		return "redirect:/gallery/detail?gallery_id="+gallery.getGallery_id();
	}
	
	//삭제
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(int gallery_id) {
		galleryDAO.delete(gallery_id);
		
		return "redirect:/gallery/list";
	}
}