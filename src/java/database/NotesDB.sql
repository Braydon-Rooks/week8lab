/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  671978
 * Created: Oct 25, 2017
 */

DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;


DROP TABLE Note;

CREATE TABLE note( 
    noteId INT(10) NOT NULL AUTO_INCREMENT,
    dateCreated DATETIME NOT NULL,
    contents VARCHAR(10000) NOT NULL,
    
    PRIMARY KEY (noteId)
);

