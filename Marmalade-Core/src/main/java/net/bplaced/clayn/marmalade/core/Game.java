package net.bplaced.clayn.marmalade.core;

import java.io.File;
import java.net.URI;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class Game extends ChangeableObject<Game> 
{
    private String name="";
    private String description="";
    private File executable=null;
    private URI image=null;

    public Game()
    {
    }

    public Game(Game orig) {
        name=orig.getName();
        description=orig.getDescription();
        executable=orig.getExecutable();
        image=orig.getImage();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public File getExecutable()
    {
        return executable;
    }

    public URI getImage()
    {
        return image;
    }

    public void setImage(URI image)
    {
        this.image = image;
    }
    
    

    public void setExecutable(File executable)
    {
        this.executable = executable;
    }

    @Override
    public String toString()
    {
        return "Game{" + "name=" + name + ", description=" + description + ", executable=" + executable + ", image=" + image + '}';
    }
    
    
}
