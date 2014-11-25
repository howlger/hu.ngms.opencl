package hu.ngms.opencl.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.PlatformUI;

public class OpenCLMarkerFactory {

	public static IMarker createMarker(IResource res) throws CoreException {
		IMarker marker = null;
		marker = res.createMarker(Activator.PLUGIN_ID);
		marker.setAttribute("description", "OpenCL marker");
		marker.setAttribute(IMarker.MESSAGE, "OpenCL marker");
		return marker;
	}

	public static List<IMarker> findMarkers(IResource resource) {
		try {
			return Arrays.asList(resource.findMarkers(Activator.PLUGIN_ID, true, IResource.DEPTH_ZERO));
		} catch (CoreException e) {
			return new ArrayList<IMarker>();
		}
	}

	public static List<IMarker> findAllMarkers(IResource resource) {
		try {
			return Arrays.asList(resource.findMarkers(Activator.PLUGIN_ID, true, IResource.DEPTH_INFINITE));
		} catch (CoreException e) {
			return new ArrayList<IMarker>();
		}
	}

	public static TreeSelection getTreeSelection() {

		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
				.getSelection();
		if (selection instanceof TreeSelection) {
			return (TreeSelection) selection;
		}
		return null;
	}

}
