package com.contractor.app.fileroom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<FolderVO> selectFolders(Integer fileRoomsNo) {
		return fileRoomMapper.selectFolders(fileRoomsNo);
	}
	
	@Override
	public FolderVO selectFolder(FolderVO folderVO) {
		return fileRoomMapper.selectFolder(folderVO);
	}
	
	@Override
	public List<FilesVO> selectFiles(Integer fileRoomsNo) {
		return fileRoomMapper.selectFiles(fileRoomsNo);
	}
	
	@Override
	public List<FolderFileVO> selectFolderFile(FolderFileVO folderFileVO) {
		return fileRoomMapper.selectFolderFile(folderFileVO);
	}

	@Override
	public int countFolder(Integer fileRoomsNo) {
		return fileRoomMapper.countFolder(fileRoomsNo);
	}
	
	@Override
	public int countFile(Integer fileRoomsNo) {
		return fileRoomMapper.countFile(fileRoomsNo);
	}
	
	@Override
	public int insertFile(FilesVO filesVO) {
		return fileRoomMapper.insertFile(filesVO);
	}

	@Override
	public int deleteFolder(int folderNo) {
		return fileRoomMapper.deleteFolder(folderNo);
	}

	@Override
	public int deleteFile(int fileNo) {
		return fileRoomMapper.deleteFile(fileNo);
	}

	@Override
	public int insertFolder(FolderVO folderVO) {
		return fileRoomMapper.insertFolder(folderVO);
	}

}
