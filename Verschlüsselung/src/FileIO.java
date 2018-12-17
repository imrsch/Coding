import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileIO 
{
	String path;
	String content;
	
	//Method: Read and Write 
	public FileIO(String path, String content, boolean read_write) throws IOException
	{
		//boolean is changed in the other class
		//read = false
		//write = true
		if(read_write == true)
		{	
			//Write
			//Write the content in the given path, then turning the content into Bytes
			writeFile(path, content.getBytes());
			this.content = content;
			this.path = path;
		}
		else if( read_write == false)
		{
			//Read
			//Get the Bytes of the given path and turn them into a String 			 
			this.path = path;
			this.content = openFileToString(readFile(path));			
		}			
	}
	
	//-------Fischer--------
	public byte[] readFile(String fileName) throws IOException 
	{        
		File file = new File(fileName);
		
	    long length = file.length();
	    if (length > Integer.MAX_VALUE) 
	    {
	        // File is too large
	        throw new IOException("File is too large!");
	    }
	
	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];
	
	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	
	    InputStream is = new FileInputStream(file);
	    try 
	    {
	        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) 
	        {
	            offset += numRead;
	        }
	    } 
	    finally 
	    {
	        is.close();
	    }
	
	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) 
	    {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
		
	    return bytes;
	}		
	public void writeFile(String fileName, byte[] buf)
	{
		
		FileOutputStream fos = null;
		
		try
		{
		   fos = new FileOutputStream(fileName);
		   fos.write(buf);
		}
		catch(IOException ex)
		{
		   System.out.println(ex);
		}
		finally
		{
		   if(fos!=null)
		      try
		      {
		         fos.close();
		      }
		      catch(Exception ex)
		      {
		    	  
		      }
		}
	}
	//---------------------
	
	//Convert the bytes to a string
	public String openFileToString(byte[] _bytes)
	{
	    String file_string = "";

	    for(int i = 0; i < _bytes.length; i++)
	    {
	        file_string += (char)_bytes[i];
	    }

	    return file_string;    
	}
}