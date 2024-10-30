package com.contractor.app.fileroom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.board.service.PagingVO;
import com.contractor.app.fileroom.mapper.FileRoomMapper;
import com.contractor.app.fileroom.service.FileRoomService;
import com.contractor.app.fileroom.service.FileRoomsVO;
import com.contractor.app.fileroom.service.FilesVO;
import com.contractor.app.fileroom.service.FolderFileVO;
import com.contractor.app.fileroom.service.FolderVO;

@Service
public class FileRoomServiceImpl implements FileRoomService {
	private FileRoomMapper fileRoomMapper;
	
	@Autowired
	public FileRoomServiceImpl(FileRoomMapper fileRoomMapper) {
		this.fileRoomMapper = fileRoomMapper;
	}
	
	@Override
	public List<FileRoomsVO> selectFilerooms(FileRoomsVO fileRoomsVO) {
		return fileRoomMapper.selectFilerooms(fileRoomsVO);
	}
	
	@Override
	public FileRoomsVO selectFileroom(FileRoomsVO fileRoomsVO) {
		return fileRoomMapper.selectFileroom(fileRoomsVO);
	}
	
	@Override
	public List<FolderVO> selectFolders(FolderVO folderVO) {
		return fileRoomMapper.selectFolders(folderVO);
	}
	
	@Override
	public List<FolderFileVO> selectFolderFile(PagingVO pagingVO, FolderFileVO folderFileVO) {
		return fileRoomMapper.selectFolderFile(pagingVO, folderFileVO);
	}

	@Override
	public int countFolderFile(FolderFileVO folderFileVO) {
		return fileRoomMapper.countFolderFile(folderFileVO);
	}
	
	@Override
	public int insertFile(FilesVO filesVO) {
		return fileRoomMapper.insertFile(filesVO);
	}

}
