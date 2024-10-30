package com.contractor.app.fileroom.mapper;

import java.util.List;

import com.contractor.app.board.service.PagingVO;
import com.contractor.app.fileroom.service.FileRoomsVO;
import com.contractor.app.fileroom.service.FilesVO;
import com.contractor.app.fileroom.service.FolderFileVO;
import com.contractor.app.fileroom.service.FolderVO;

public interface FileRoomMapper {
	// 자료실 전체조회
	public List<FileRoomsVO> selectFilerooms(FileRoomsVO fileRoomsVO);
	
	// 자료실 단건조회
	public FileRoomsVO selectFileroom(FileRoomsVO fileRoomsVO);

	// 자료실별 폴더 전체조회
	public List<FolderVO> selectFolders(FolderVO folderVO);
	
	// 자료실별 폴더, 파일 전체조회
	public List<FolderFileVO> selectFolderFile(PagingVO pagingVO, FolderFileVO folderFileVO);

	// 총 폴더/파일 갯수
	public int countFolderFile(FolderFileVO folderFileVO);

	// 파일 업로드
	public int insertFile(FilesVO filesVO);
}
