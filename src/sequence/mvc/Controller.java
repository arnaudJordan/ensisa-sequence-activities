package sequence.mvc;

public class Controller {

	private Model model;
    private View view;
            
    public Controller(Model model, View view) {
        this.setModel(model);
        this.setView(view);
    }

	public void setModel(Model model) {
		this.model = model;
	}

	public Model getModel() {
		return model;
	}

	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}
}

